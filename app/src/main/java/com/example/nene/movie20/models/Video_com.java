package com.example.nene.movie20.models;

import java.util.List;

/**
 * Created by sam- on 2018/5/9.
 */

public class Video_com {
    private int count;
    private String next;
    private String previous;
    private List<Results> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = (List<Results>) results;
    }

    public static class Results{
        public int id;
        public User user;
        public String video;
        public String comment;
        public int point_love_nums;
        public boolean is_love;
        public String add_time;
        public List<Child_com> child_com;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getPoint_love_nums() {
            return point_love_nums;
        }

        public void setPoint_love_nums(int point_love_nums) {
            this.point_love_nums = point_love_nums;
        }

        public boolean isIs_love() {
            return is_love;
        }

        public void setIs_love(boolean is_love) {
            this.is_love = is_love;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Child_com getChild_com() {
            return (Child_com) child_com;
        }

        public void setChild_com(Child_com child_com) {
            this.child_com = (List<Child_com>) child_com;
        }

        public static class Child_com{
            public int id;
            public int comment_id;
            public int reply_id;
            public From_uid from_uid;
            public To_uid to_uid;
            public int reply_type;
            public String comment;
            public int point_love_nums;
            public boolean is_love;
            public String add_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public int getReply_id() {
                return reply_id;
            }

            public void setReply_id(int reply_id) {
                this.reply_id = reply_id;
            }

            public From_uid getFrom_uid() {
                return from_uid;
            }

            public void setFrom_uid(From_uid from_uid) {
                this.from_uid = from_uid;
            }

            public To_uid getTo_uid() {
                return to_uid;
            }

            public void setTo_uid(To_uid to_uid) {
                this.to_uid = to_uid;
            }

            public int getReply_type() {
                return reply_type;
            }

            public void setReply_type(int reply_type) {
                this.reply_type = reply_type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getPoint_love_nums() {
                return point_love_nums;
            }

            public void setPoint_love_nums(int point_love_nums) {
                this.point_love_nums = point_love_nums;
            }

            public boolean isIs_love() {
                return is_love;
            }

            public void setIs_love(boolean is_love) {
                this.is_love = is_love;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public static class From_uid{
                public int id;
                public String mobile;
                public String row_id;
                public User_profile user_profile;

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

                public User_profile getUser_profile() {
                    return user_profile;
                }

                public void setUser_profile(User_profile user_profile) {
                    this.user_profile = user_profile;
                }
            }
            public static class To_uid{
                public int id;
                public String mobile;
                public String row_id;
                public User_profile user_profile;

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

                public User_profile getUser_profile() {
                    return user_profile;
                }

                public void setUser_profile(User_profile user_profile) {
                    this.user_profile = user_profile;
                }
            }
        }

    }
}
