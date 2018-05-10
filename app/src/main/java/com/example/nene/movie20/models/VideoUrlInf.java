package com.example.nene.movie20.models;

/**
 * Created by sam- on 2018/5/8.
 */

public class VideoUrlInf {
    private int id;
    private String video_name;
    private String desc;
    private String video_img;
    private String click_num;
    private String video_kind;
    private String url;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVideo_img() {
        return video_img;
    }

    public void setVideo_img(String video_img) {
        this.video_img = video_img;
    }

    public String getClick_num() {
        return click_num;
    }

    public void setClick_num(String click_num) {
        this.click_num = click_num;
    }

    public String getVideo_kind() {
        return video_kind;
    }

    public void setVideo_kind(String video_kind) {
        this.video_kind = video_kind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    protected static class User{
        public int id;
        public String mobile;
        public String row_id;
        public User_Profile user_profile;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRow_id() {
            return row_id;
        }

        public void setRow_id(String row_id) {
            this.row_id = row_id;
        }

        public User_Profile getUser_profile() {
            return user_profile;
        }

        public void setUser_profile(User_Profile user_profile) {
            this.user_profile = user_profile;
        }
    }

    public static class User_Profile{
        public String image;
        public String birth;
        public String sex;
        public String address;
        public String nick_name;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }
    }

}
