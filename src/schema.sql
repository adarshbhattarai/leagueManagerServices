-- Drop table

-- DROP TABLE public.league;

CREATE TABLE public.league (
	league_id serial NOT NULL,
	league_name varchar(50) NOT NULL,
	league_description varchar(255) NULL,
	league_stage varchar(20) NULL,
	created_on timestamp NOT NULL,
	CONSTRAINT league_pkey PRIMARY KEY (league_id)
);



-- Drop table

-- DROP TABLE public.league;

CREATE TABLE public.league (
	league_id serial NOT NULL,
	league_name varchar(50) NOT NULL,
	league_description varchar(255) NULL,
	league_stage varchar(20) NULL,
	created_on timestamp NOT NULL,
	CONSTRAINT league_pkey PRIMARY KEY (league_id)
);


-- Drop table

-- DROP TABLE public.league;

CREATE TABLE public.league (
	league_id serial NOT NULL,
	league_name varchar(50) NOT NULL,
	league_description varchar(255) NULL,
	league_stage varchar(20) NULL,
	created_on timestamp NOT NULL,
	CONSTRAINT league_pkey PRIMARY KEY (league_id)
);

-- Drop table

-- DROP TABLE public.game_format;

CREATE TABLE public.game_format (
	format_id serial NOT NULL,
	format_name varchar(50) NOT NULL,
	format_desc varchar(255) NOT NULL,
	nxtrnd_ptcpnt int4 NULL,
	winner int4 NOT NULL,
	next_round_id int4 NULL,
	CONSTRAINT game_format_pkey PRIMARY KEY (format_id)
);



create table game(
	game_id serial not null primary key,
	home_player int4 references player(player_id),
	away_player int4 references player(player_id),
	game_links varchar(50)[],
	game_stat varchar(10) not null,
	description varchar(255),
	played_on timestamp
)




--Hibernate: create table league_admin (group_id int8 not null, admin_id int8 not null, league_id int8 not null, primary key (league_id, admin_id))

--Hibernate: alter table if exists player add column dtype varchar(31) not null

