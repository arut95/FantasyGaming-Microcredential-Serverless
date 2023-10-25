package mobile.gaming;

import java.util.Set;

public class PlayerVoteState {
    private int id;
    private String name;
    private Set<String> uniqueUsers;

    public PlayerVoteState(int id, String name, Set<String> uniqueUsers) {
        this.id = id;
        this.name = name;
        this.uniqueUsers = uniqueUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalVotes() {
        return uniqueUsers.size();
    }

    public Set<String> getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(Set<String> uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    @Override
    public String toString() {
        return "PlayerVoteState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uniqueUsers=" + uniqueUsers +
                '}';
    }

}
