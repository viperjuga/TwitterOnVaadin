using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace DBaseAgent
{
    public class DataFunctions
    {
        public static User GetUser(string username, string password)
        {
            var user = new User();
            using (var db = new TwitterOnVaadinEntities())
            {
                user = db.Users.ToList().FirstOrDefault(e => e.Username == username && e.Password == password);
                //if (userDb != null)
                //{
                //    user.Username = userDb.Username;
                //    user.Forename = userDb.Forename;
                //    user.Surname = userDb.Surname;
                //    user.Age = userDb.Age;
                //    user.Password = userDb.Password;
                //    user.Photo = userDb.Photo;
                //    user.id = userDb.id;
                //    user.Admin = userDb.Admin;
                //    user.Ban = userDb.Ban;
                //}
            }
            return user;

        }
        public static bool UpdateUser(User newUser)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var user = db.Users.FirstOrDefault(e => e.id == newUser.id);
                if (user != null)
                {
                    user = newUser;
                }
                db.SaveChanges();
            }
            return true;
        }
        public static bool AddUser(User newUser)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                if (db.Users.FirstOrDefault(e => e.Username == newUser.Username) != null)
                {
                    db.Users.AddObject(newUser);
                    db.SaveChanges();
                    return true;
                }
                return false;
            }
        }

        public static List<User> GetAllUsers()
        {
            var users = new List<User>();
            using (var db = new TwitterOnVaadinEntities())
            {
                users = db.Users.ToList();
                //if (usersDb != null)
                //{
                //    foreach (var userDb in usersDb)
                //    {
                //        users.Add(new User{
                //             Username = userDb.Username,
                //             Forename = userDb.Forename,
                //             Surname = userDb.Surname,
                //             Age = userDb.Age,
                //             Password = userDb.Password,
                //             Photo = userDb.Photo,
                //             id = userDb.id,
                //             Admin = userDb.Admin,
                //             Ban = userDb.Ban,
                //        });
                //    }
                //}
            }
            return users;
        }

        public static List<Post> GetPosts(int userId)
        {
            var posts = new List<Post>();
            using (var db = new TwitterOnVaadinEntities())
            {
                posts = db.Posts.Where(e => e.User == userId).ToList();
            }
            return posts;
        }
        public static bool AddPost(Post newPost)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                newPost.id = 1;
                db.Posts.AddObject(newPost);
                db.SaveChanges();
            }
            return true;
        }
        public static bool DeletePost(int postId)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var post = db.Posts.FirstOrDefault(e => e.id == postId);
                db.Posts.DeleteObject(post);
                db.SaveChanges();
            }
            return true;
        }
        public static bool UpdatePost(Post newPost)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var post = db.Posts.FirstOrDefault(e => e.id == newPost.id);
                if (post != null)
                {
                    post = newPost;
                }
                db.SaveChanges();
            }
            return true;
        }

        public static List<User> GetBlockedUsers(int userId)
        {
            var users = new List<User>();
            using (var db = new TwitterOnVaadinEntities())
            {
                var blockedUsers = db.BlockedLists.Where(e => e.User == userId).ToList();
                foreach (var temp in blockedUsers)
                {
                    users.Add(GetAllUsers().FirstOrDefault(e => e.id == temp.BlockedUser));
                }
            }
            return users;
        }
        public static bool DeleteBlockedUser(int userId, int blockedId)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var blockedUsers = db.BlockedLists.FirstOrDefault(e => e.User == userId && e.BlockedUser == blockedId);
                db.BlockedLists.DeleteObject(blockedUsers);
                db.SaveChanges();
            }
            return true;
        }
        public static bool AddBlockedUser(int userId, int blockedId)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var blockedUsers = new BlockedList
                {
                    BlockedUser = blockedId,
                    User = userId,
                };

                db.BlockedLists.AddObject(blockedUsers);
                db.SaveChanges();
            }
            return true;
        }

        public static List<User> GetFriendUsers(int userId)
        {
            var users = new List<User>();
            using (var db = new TwitterOnVaadinEntities())
            {
                var friendUsers = db.FriendLists.Where(e => e.User == userId).ToList();
                foreach (var temp in friendUsers)
                {
                    users.Add(GetAllUsers().FirstOrDefault(e => e.id == temp.Friend));
                }
            }
            return users;
        }
        public static bool DeleteFriendUser(int userId, int friendId)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var friendUser = db.FriendLists.FirstOrDefault(e => e.User == userId && e.Friend == friendId);
                db.FriendLists.DeleteObject(friendUser);
                db.SaveChanges();
            }
            return true;
        }
        public static bool AddFriendUser(int userId, int friendId)
        {
            using (var db = new TwitterOnVaadinEntities())
            {
                var friendUser = new FriendList
                {
                    Friend = friendId,
                    User = userId,
                };

                db.FriendLists.AddObject(friendUser);
                db.SaveChanges();
            }
            return true;
        }
    }
}
