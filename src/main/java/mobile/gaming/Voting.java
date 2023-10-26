package mobile.gaming;

import java.util.*;

public class Voting {

    private static final Map<Integer, PlayerVoteState> voteStateMap = new HashMap<>();

    static {
        PlayerVoteState player1 = new PlayerVoteState(1, "Gerard Pique", new HashSet<>());
        PlayerVoteState player2 = new PlayerVoteState(2, "Lionel Messi", new HashSet<>());
        PlayerVoteState player3 = new PlayerVoteState(3, "Sergio Busquets", new HashSet<>());
        PlayerVoteState player4 = new PlayerVoteState(4, "Marc-Andre ter Stegen", new HashSet<>());
        PlayerVoteState player5 = new PlayerVoteState(5, "Xavi", new HashSet<>());
        PlayerVoteState player6 = new PlayerVoteState(6, "Andres Iniesta", new HashSet<>());
        PlayerVoteState player7 = new PlayerVoteState(7, "Sergi Roberto", new HashSet<>());
        PlayerVoteState player8 = new PlayerVoteState(8, "Carles Puyol", new HashSet<>());
        PlayerVoteState player9 = new PlayerVoteState(9, "Jordi Alba", new HashSet<>());
        PlayerVoteState player10 = new PlayerVoteState(10, "Dani Alves", new HashSet<>());
        PlayerVoteState player11 = new PlayerVoteState(11, "Pedri", new HashSet<>());

        voteStateMap.put(player1.getId(), player1);
        voteStateMap.put(player2.getId(), player2);
        voteStateMap.put(player3.getId(), player3);
        voteStateMap.put(player4.getId(), player4);
        voteStateMap.put(player5.getId(), player5);
        voteStateMap.put(player6.getId(), player6);
        voteStateMap.put(player7.getId(), player7);
        voteStateMap.put(player8.getId(), player8);
        voteStateMap.put(player9.getId(), player9);
        voteStateMap.put(player10.getId(), player10);
        voteStateMap.put(player11.getId(), player11);
    }

    public static PlayerVoteState getVoteState(int playerId) {
        return voteStateMap.get(playerId);
    }

    public static void setVoteState(Integer playerId, PlayerVoteState playerVoteState) {
        voteStateMap.put(playerId, playerVoteState);
    }

    public static PlayerVoteState castVote(int playerId, String username) {
        PlayerVoteState playerVoteState = getVoteState(playerId);
        playerVoteState.getUniqueUsers().add(username);

        setVoteState(playerId, playerVoteState);

        return playerVoteState;
    }

    public static Map<Integer, PlayerVoteState> getVoteResults() {
        return voteStateMap;
    }

}

