AWS Elastic Beanstalk Deploys a Jar in a web container.

AWS EB exposes port 5000 to run its applications.

Often time we want to modify this, 

So, instead of uploading just the jar, we can upload a zip file
which contains jar and a folder named ".ebextensions" 
which "aws-eb" uses to configuration called https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/ebextensions.html


1) How to create a build that generates a zip file which has this configurations?

Answer to that is to add a maven assembly plugin which bundles out application the way we want.
 
        <build>
         <plugins>
             <plugin>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-maven-plugin</artifactId>
             </plugin>
             <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-assembly-plugin</artifactId>
                 <configuration>
                     <descriptors>
                         <descriptor>
                             assembly/bin.xml
                         </descriptor>
                     </descriptors>
                     <finalName>soccer-manager-${project.version}</finalName>
                     <appendAssemblyId>false</appendAssemblyId>
                     <attach>false</attach>
                 </configuration>
                 <executions>
                     <execution>
                         <phase>package</phase>
                         <goals><goal>single</goal></goals>
                     </execution>
                 </executions>
             </plugin>
         </plugins>
     </build>
 
 We have also included a descriptor file here which defines what files to use
 and how to package.
 (Refer: https://maven.apache.org/plugins/maven-assembly-plugin/descriptor-refs.html)
 
 
 Since we want to package .ebextensions next to the jar file ,
 (Refer : https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-se-platform.html)
 our descriptor `assembly/bin.xml` will include all the necessary files and folders to be packaged:
 
     <fileSets>
         <fileSet>
             <directory>${project.basedir}/ebextensions/</directory>
             <outputDirectory>.ebextensions/</outputDirectory>
             <includes>
                 <include>*</include>
                 <include>nginx/conf.d/https.conf</include>
             </includes>
         </fileSet>
     </fileSets>
     <files>
        <file>
                 <source>${project.build.directory}/${artifactId}-${project.version}.${packaging}
                 </source>
                 <outputDirectory></outputDirectory>
                 <destName>${artifactId}.${packaging}</destName>
             </file>
         </files>
     
 Now when we run `mvn package` we will see a bundled file(zip) (look at `<formats></formats>` )
 
 2) How to secure it with SSL.
 You can either have your own certificates and use it like mentioned here
 (https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/https-singleinstance-java.html)
 
 Or under ebextension we provide nginx config and use letsencrypt ssl certificates. <br>
 From Amazon: <br>
 `The Elastic Beanstalk Java SE platform includes an nginx server that acts as a reverse proxy, serving cached static content and passing requests to your application. The platform provides configuration options to configure the proxy server to serve static assets from a folder in your source code to reduce the load on your application. For advanced scenarios, you can include your own .conf files in your source bundle to extend Elastic Beanstalk's proxy configuration or overwrite it completely.`
 
 For this create this ebextensions folder with  nginx
 
 Structure is as :<br>
 
   - |-assembly<br>
     - |------bin.xml 
   <br>
  - |-ebextensions<br>
    - |------nginx<br>
       - |------------conf.d<br>
            - |------------------https.conf <br>
     - |------certbot.confif<br>
 - |-Readmes<br>
     - |-----letsencrypt_ssl_elasticbeanstalk<br>
 - |-src<br>
     - |-------main
 
 Update your certbot.config
        
        Resources:
          sslSecurityGroupIngress:
            Type: AWS::EC2::SecurityGroupIngress
            Properties:
              GroupId: {"Fn::GetAtt" : ["AWSEBSecurityGroup", "GroupId"]}
              IpProtocol: tcp
              ToPort: 443
              FromPort: 443
              CidrIp: 0.0.0.0/0
        
        packages:
          yum:
            epel-release: []
        
        container_commands:
          00_create_dir:
            command: "mkdir -p /opt/certbot"
          10_installcertbotandchmod:
            command: "wget https://dl.eff.org/certbot-auto -O /opt/certbot/certbot-auto;chmod a+x /opt/certbot/certbot-auto"
          20_getcert:
            command: "sudo /opt/certbot/certbot-auto certonly --standalone --debug --non-interactive --email ${your-email@gmail.com} --agree-tos --domains ${yourbeanstalk.com} \
            --expand --renew-with-new-domains --pre-hook \"service nginx stop\""
          30_link:
            command: "sudo ln -sf /etc/letsencrypt/live/${yourbeanstalk.com} /etc/letsencrypt/live/ebcert"
          40_restart_nginx:
            command: "sudo service nginx restart"
          50_cronjobsetrenewal:
            command: '(crontab -l ; echo ''0 6 * * * root /opt/certbot/certbot-auto renew --standalone --pre-hook "service nginx stop" --post-hook "sudo service nginx start" --force-renew'') | crontab -'
 
 
 
 Update your `${your-email@gmail.com}` and `${yourbeanstalk.com}` in above file
 And in your https.conf  update it as below
 
 
 
    # HTTPS server
    
    server {
        listen       443;
        server_name  localhost;
    
        ssl                  on;
        ssl_certificate      /etc/letsencrypt/live/ebcert/fullchain.pem;
        ssl_certificate_key  /etc/letsencrypt/live/ebcert/privkey.pem;
    
        ssl_session_timeout  5m;
    
        ssl_protocols  TLSv1 TLSv1.1 TLSv1.2;
        ssl_ciphers "EECDH+AESGCM:EDH+AESGCM:AES256+EECDH:AES256+EDH";
        ssl_prefer_server_ciphers   on;
    
        location / {
            proxy_pass  http://localhost:${app_port};
            proxy_set_header   Connection "";
            proxy_http_version 1.1;
            proxy_set_header        Host            $host;
            proxy_set_header        X-Real-IP       $remote_addr;
            proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header        X-Forwarded-Proto https;
        }
    }
 
Refer : https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/https-singleinstance-java.html

provide app_port (your application port number)
 in certbot.config we first get the certificate and put it in appropriate dir and link it and restart the nginx. Look at    
 
 And in https.conf used by nginx we provide that linked certificates and route all the ssl requests 
 to spring boot port
 