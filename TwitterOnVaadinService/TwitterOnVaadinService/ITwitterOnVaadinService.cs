﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace TwitterOnVaadinService
{
    [ServiceContract]
    public interface ITwitterOnVaadinService
    {

        [OperationContract]
        UserDB GetUser(string Username, string Password);
        [OperationContract]
        bool UpdateUser(string Username, string Forename, string Surname, int Age, string Password, string Photo, bool Ban, int Id);
        [OperationContract]
        bool AddNewUser(string Username, string Forename, string Surname, int Age, string Password, string Photo);
        [OperationContract]
        List<UserDB> GetAllUsers();

        [OperationContract]
        List<PostDB> GetPosts(int userId);
        [OperationContract]
        bool AddPost(string Post, int Author, int User, string Date);
        [OperationContract]
        bool DeletePost(int postId);
        [OperationContract]
        bool UpdatePost(string Post, int Author, int User, string Date, int Id);

        [OperationContract]
        List<UserDB> GetBlockedUsers(int userId);
        [OperationContract]
        bool DeleteBlockedUser(int userId, int blockedId);
        [OperationContract]
        bool AddBlockedUser(int userId, int blockedId);

        [OperationContract]
        List<UserDB> GetFriendUsers(int userId);
        [OperationContract]
        bool DeleteFriendUser(int userId, int friendId);
        [OperationContract]
        bool AddFriendUser(int userId, int friendId);
    }

    [DataContract]
    public class UserDB
    {
        [DataMember]
        public int Id { get; set; }
        [DataMember]
        public string Forename { get; set; }
        [DataMember]
        public string Surname { get; set; }
        [DataMember]
        public int Age { get; set; }
        [DataMember]
        public string Username { get; set; }
        [DataMember]
        public string Password { get; set; }
        [DataMember]
        public string Photo { get; set; }
        [DataMember]
        public bool Admin { get; set; }
        [DataMember]
        public bool Ban { get; set; }
    }
    [DataContract]
    public class PostDB
    {
        [DataMember]
        public int Id { get; set; }
        [DataMember]
        public string Post { get; set; }
        [DataMember]
        public int Author { get; set; }
        [DataMember]
        public string Date { get; set; }
        [DataMember]
        public string User { get; set; }
    }

}
