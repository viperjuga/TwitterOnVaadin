using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using DBaseAgent;

namespace TwitterOnVaadinService
{
    public class TwitterOnVaadinService : ITwitterOnVaadinService
    {
        public UserDB GetUser(string Username, string Password)
        {
            var user = new UserDB();
            try
            {
                var res = DataFunctions.GetUser(Username, Password);
                user = new UserDB
                {
                    Username = res.Username,
                    Forename = res.Forename,
                    Surname = res.Surname,
                    Age = (int)res.Age,
                    Password = res.Password,
                    Photo = res.Photo,
                    Id = res.id,
                    Admin = res.Admin,
                    Ban = res.Ban,
                };
            }
            catch (Exception ex)
            {
                //TODO:Logging
            }
            return user;
        }
        public bool UpdateUser(string Username, string Forename, string Surname, int Age, string Password, string Photo, bool Ban, int Id)
        {
            try
            {
               var userDb = new User
               {
                    Username = Username,
                    Forename = Forename,
                    Surname = Surname,
                    Age = Age,
                    Password = Password,
                    Photo = Photo,
                    id = Id,
                    Ban = Ban,
               };
               var res = DataFunctions.UpdateUser(userDb);
            }catch(Exception ex)
            {
                //TODO:Logging
                return false;
            }
            return true;;
        }

        public bool AddNewUser(string Username, string Forename, string Surname, int Age, string Password, string Photo)
        {
            try
            {
                var userDb = new User
                {
                    Username = Username,
                    Forename = Forename,
                    Surname = Surname,
                    Age = Age,
                    Password = Password,
                    Photo = Photo,
                    Admin = false,
                    Ban = false,
                };
                var res = DataFunctions.AddUser(userDb);
            }
            catch (Exception ex)
            {
                //TODO:Logging
                return false;
            }
            return true;
        }

        public List<UserDB> GetAllUsers()
        {
            var users = new List<UserDB>();
            try
            {
                var res = DataFunctions.GetAllUsers();
                foreach (var temp in res)
                {
                    users.Add(new UserDB
                    {
                        Username = temp.Username,
                        Forename = temp.Forename,
                        Surname = temp.Surname,
                        Age = (int)temp.Age,
                        Password = temp.Password,
                        Photo = temp.Photo,
                        Id = temp.id,
                        Admin = temp.Admin,
                        Ban = temp.Ban,
                    });
                }
            }
            catch (Exception ex)
            {
                //TODO: Logging
            }
            return users;
        }

        public List<PostDB> GetPosts(int userId)
        {
            var posts = new List<PostDB>();
            try
            {
                var res = DataFunctions.GetPosts(userId);
                foreach (var temp in res)
                {
                    posts.Add(new PostDB
                    {
                        Post = temp.Post1,
                        Author = temp.Author,
                        User = temp.User.ToString(),
                        Id = temp.id,
                        Date = temp.Date,
                    });
                }
            }
            catch (Exception ex)
            {
                //TODO: Logging
            }
            return posts;
        }
        public bool AddPost(string Post, int Author, int User, string Date)
        {
            bool res = false;
            try
            {
                var post = new Post{
                    Post1 = Post,
                    Author = Author,
                    User = User,
                    Date = Date,
                };
                res = DataFunctions.AddPost(post);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return res;
        }
        public bool DeletePost(int postId)
        {
            bool res = false;
            try
            {
                res = DataFunctions.DeletePost(postId);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return false;
        }
        public bool UpdatePost(string Post, int Author, int User, string Date, int Id)
        {
            bool res = false;
            try
            {
                var post = new Post
                {
                    Post1 = Post,
                    Author = Author,
                    User = User,
                    Date = Date,
                    id = Id,
                };
                res = DataFunctions.UpdatePost(post);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return res;
        }

        public List<UserDB> GetBlockedUsers(int userId)
        {
            var blockedUsers = new List<UserDB>();
            try
            {
                var res = DataFunctions.GetBlockedUsers(userId);
                foreach (var temp in res)
                {
                    blockedUsers.Add(new UserDB
                    {
                        Username = temp.Username,
                        Forename = temp.Forename,
                        Surname = temp.Surname,
                        Age = (int)temp.Age,
                        Password = temp.Password,
                        Photo = temp.Photo,
                        Id = temp.id,
                        Admin = temp.Admin,
                        Ban = temp.Ban,  
                    });
                }
            }
            catch (Exception ex)
            {
                //TODO: Logging
            }
            return blockedUsers;
        }
        public bool DeleteBlockedUser(int userId, int blockedId)
        {
            bool res = false;
            try
            {
                res = DataFunctions.DeleteBlockedUser(userId, blockedId);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return res;
        }
        public bool AddBlockedUser(int userId, int blockedId)
        {
            bool res;
            try
            {
                res = DataFunctions.AddBlockedUser(userId, blockedId);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return res;
        }

        public List<UserDB> GetFriendUsers(int userId)
        {
            var friendUsers = new List<UserDB>();
            try
            {
                var res = DataFunctions.GetFriendUsers(userId);
                foreach (var temp in res)
                {
                    friendUsers.Add(new UserDB
                    {
                        Username = temp.Username,
                        Forename = temp.Forename,
                        Surname = temp.Surname,
                        Age = (int)temp.Age,
                        Password = temp.Password,
                        Photo = temp.Photo,
                        Id = temp.id,
                        Admin = temp.Admin,
                        Ban = temp.Ban,
                    });
                }
            }
            catch (Exception ex)
            {
                //TODO: Logging
            }
            return friendUsers;
        }
        public bool DeleteFriendUser(int userId, int friendId)
        {
            bool res = false;
            try
            {
                res = DataFunctions.DeleteBlockedUser(userId, friendId);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return true;
        }
        public bool AddFriendUser(int userId, int friendId)
        {
            bool res = false;
            try
            {
                res = DataFunctions.AddFriendUser(userId, friendId);
            }
            catch (Exception ex)
            {
                //TODO: Logging
                return false;
            }
            return res;
        }
    }
}
