package cn.pengxun.vshop.mvp.model.entity;

/**
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public class UserEntity {
    private int id;
    private String username;
    private String avatar_url;

    public UserEntity(int id, String login, String avatar_url) {
        this.id = id;
        this.username = login;
        this.avatar_url = avatar_url;
    }

    public String getAvatarUrl() {
        if (avatar_url.isEmpty()) return avatar_url;
        return avatar_url.split("\\?")[0];
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
