package dantakuti.games.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author adarshbhattarai on 2020-05-08
 * @project LeagueManager
 */
@Entity
public class GameFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long formatId;
    String formatName;
    String formatDesc;
    int nxtrndPtcpnt;
    int winner;
    Long nextRoundId;

    public long getFormatId() {
        return formatId;
    }

    public void setFormatId(long formatId) {
        this.formatId = formatId;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getFormatDesc() {
        return formatDesc;
    }

    public void setFormatDesc(String formatDesc) {
        this.formatDesc = formatDesc;
    }

    public int getNxtrndPtcpnt() {
        return nxtrndPtcpnt;
    }

    public void setNxtrndPtcpnt(int nxtrndPtcpnt) {
        this.nxtrndPtcpnt = nxtrndPtcpnt;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public Long getNextRoundId() {
        return nextRoundId;
    }

    public void setNextRoundId(Long nextRoundId) {
        this.nextRoundId = nextRoundId;
    }
}
