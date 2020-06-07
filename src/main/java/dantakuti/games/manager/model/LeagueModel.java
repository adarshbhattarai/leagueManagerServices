package dantakuti.games.manager.model;

import javax.validation.constraints.NotBlank;

/**
 * @author adarshbhattarai on 2020-05-11
 * @project LeagueManager
 */
public class LeagueModel {

    @NotBlank
    private String leagueName;
    private GroupModel[] groups;
    private Long formatId;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Long getFormatId() {
        return formatId;
    }

    public void setFormatId(Long formatId) {
        this.formatId = formatId;
    }

    public GroupModel[] getGroups() {
        return groups;
    }

    public void setGroups(GroupModel[] groups) {
        this.groups = groups;
    }
}
