using System;
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
        UserDB GetUser(string username, string password);
        [OperationContract]
        bool UpdateUser(UserDB newUser);

        [OperationContract]
        List<UserDB> GetAllUsers();

        [OperationContract]
        List<PostDB> GetPosts(int userId);
        [OperationContract]
        bool AddPost(PostDB newPost);
        [OperationContract]
        bool DeletePost(int postId);
        [OperationContract]
        bool UpdatePost(PostDB newPost);

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
        public int User { get; set; }
    }

}
