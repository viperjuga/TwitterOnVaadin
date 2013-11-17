package com.service;

import com.models.Post;
import com.models.User;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 11/8/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceProxy {

        HttpTransportSE mService;
        private  final String Endpoint = "http://localhost:54288/TwitterOnVaadinService.svc";
        private  final String Namespace = "http://tempuri.org/";
        private  final String OperationAddBlockedUser = "AddBlockedUser";
        private  final String OperationAddFriendUser = "AddFriendUser";
        private  final String OperationAddPost = "AddPost";
        private  final String OperationAddUser = "AddUser";
        private  final String OperationDeleteBlockedUser = "DeleteBlockedUser";
        private  final String OperationDeleteFriendUser = "DeleteFriendUser";
        private  final String OperationDeletePost = "DeletePost";
        private  final String OperationGetAllUsers = "GetAllUsers";
        private  final String OperationGetBlockedUsers = "GetBlockedUsers";
        private  final String OperationGetFriendUsers = "GetFriendUsers";
        private  final String OperationGetPosts = "GetPosts";
        private  final String OperationGetUser = "GetUser";
        private  final String OperationUpdatePost = "UpdatePost";
        private  final String OperationUpdateUser = "UpdateUser";
        private  final String OperationAction = "http://tempuri.org/ITwitterOnVaadinService/";

        private  final String PropertyId = "Id";
        private  final String PropertyForename = "Forename";
        private  final String PropertySurname = "Surname";
        private  final String PropertyUsername = "Username";
        private  final String PropertyPassword = "Password";
        private  final String PropertyPhoto = "Photo";
        private  final String PropertyBan = "Ban";
        private  final String PropertyAdmin = "Admin";
        private  final String PropertyAge = "Age";

        private  final String PropertyDate = "Date";
        private  final String PropertyUser = "User";
        private  final String PropertyAuthor = "Author";
        private  final String PropertyPost = "Post";

        private  final String PropertyFriendId = "friendId";
        private  final String PropertyUserId = "userId";
        private  final String PropertyBlockedId = "blockedId";


        public ServiceProxy() {
            mService = new HttpTransportSE(Endpoint);
        }

        private User SoapObjectToUser(SoapObject object) {
            User result = new User();
            result.setForename(object.getProperty(PropertyForename).toString());
            result.setSurname(object.getProperty(PropertySurname).toString());
            result.setAge(object.getProperty(PropertyAge).toString());
            if(object.getProperty(PropertyPhoto) != null)
                result.setPhoto(object.getProperty(PropertyPhoto).toString());
            result.setUsername(object.getProperty(PropertyUsername).toString());
            result.setId(Integer.valueOf(object.getProperty(PropertyId).toString()));
            result.setPassword(object.getProperty(PropertyPassword).toString());
            return result;
        }

        private ArrayList<User> SoapObjectToUsers(SoapObject objects) {
            ArrayList<User> result = new ArrayList<User>();
            User temp;
            for( int i=0; i < objects.getPropertyCount(); i++){
                temp = new User();
                SoapObject soapObject = (SoapObject)objects.getProperty(i);
                temp.setForename(soapObject.getProperty(PropertyForename).toString());
                temp.setSurname(soapObject.getProperty(PropertySurname).toString());
                temp.setAge(soapObject.getProperty(PropertyAge).toString());
                if(soapObject.getProperty(PropertyPhoto) != null)
                    temp.setPhoto(soapObject.getProperty(PropertyPhoto).toString());
                temp.setUsername(soapObject.getProperty(PropertyUsername).toString());
                temp.setId(Integer.valueOf(soapObject.getProperty(PropertyId).toString()));
                temp.setPassword(soapObject.getProperty(PropertyPassword).toString());
                result.add(temp);
            }
            return result;
        }

        private ArrayList<Post> SoapObjectToPosts(SoapObject objects) {
            ArrayList<Post> result = new ArrayList<Post>();
            Post temp;
            for( int i=0; i < objects.getPropertyCount(); i++){
                temp = new Post();
                SoapObject soapObject = (SoapObject)objects.getProperty(i);
                temp.setUser(soapObject.getProperty(PropertyUser).toString());
                temp.setAuthor(soapObject.getProperty(PropertyAuthor).toString());
                temp.setDate(soapObject.getProperty(PropertyDate).toString());
                temp.setPost(soapObject.getProperty(PropertyPost).toString());
                result.add(temp);
            }
            return result;
        }

        public User requestGetUser(String username, String password) throws RemoteException {
            SoapObject object = new SoapObject(Namespace, OperationGetUser);
            object.addProperty(PropertyUsername, username);
            object.addProperty(PropertyPassword, String.valueOf(password));
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(object);
            try {
                mService.call(OperationAction + OperationGetUser, envelope);
                SoapObject response = (SoapObject) envelope.getResponse();
                return SoapObjectToUser(response);
            } catch (Exception ex) {
                throw new RemoteException(ex.toString());
            }
        }

       public ArrayList<User> requestGetAllUsers() throws RemoteException {
            SoapObject object = new SoapObject(Namespace, OperationGetAllUsers);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(object);
            try {
                mService.call(OperationAction + OperationGetAllUsers, envelope);
                SoapObject response = (SoapObject) envelope.getResponse();
                return SoapObjectToUsers(response);
            } catch (Exception ex) {
                throw new RemoteException(ex.toString());
            }
       }

        public ArrayList<Post> requestGetPostForUser(String userId) throws RemoteException {
            SoapObject object = new SoapObject(Namespace, OperationGetPosts);
            object.addProperty(PropertyUserId, userId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(object);
            try {
                mService.call(OperationAction + OperationGetPosts, envelope);
                SoapObject response = (SoapObject) envelope.getResponse();
                return SoapObjectToPosts(response);
            } catch (Exception ex) {
                throw new RemoteException(ex.toString());
            }

        }
        public boolean requestAddPost(Post newPost) throws RemoteException {
            SoapObject object = new SoapObject(Namespace, OperationAddPost);
            object.addProperty(PropertyAuthor, newPost.getAuthor());
            object.addProperty(PropertyDate, newPost.getDate());
            object.addProperty(PropertyPost, newPost.getPost());
            object.addProperty(PropertyUser, newPost.getUser());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(object);

            try {
                mService.call(OperationAction + OperationAddPost, envelope);
                return Boolean.valueOf(envelope.getResponse().toString());
            } catch (Exception ex) {
                throw new RemoteException(ex.toString());
            }
        }
        public boolean requestAddUser(User newUser) throws RemoteException {
            SoapObject object = new SoapObject(Namespace, OperationAddUser);
            object.addProperty(PropertyForename, newUser.getForename());
            object.addProperty(PropertySurname, newUser.getSurname());
            object.addProperty(PropertyAge, newUser.getAge());
            object.addProperty(PropertyPhoto, newUser.getPhoto());
            object.addProperty(PropertyUsername, newUser.getUsername());
            object.addProperty(PropertyPassword, newUser.getPassword());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(object);

            try {
                mService.call(OperationAction + OperationAddUser, envelope);
                return Boolean.valueOf(envelope.getResponse().toString());
            } catch (Exception ex) {
                throw new RemoteException(ex.toString());
            }
        }
//
//        public ArrayList<ViolationType> getAllViolationType() throws RemoteException {
//            SoapObject object = new SoapObject(Namespace, OperationGetAllViolationTypes );
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.dotNet = true;
//            envelope.setOutputSoapObject(object);
//            try {
//                mService.call(OperationAction + OperationGetAllViolationTypes, envelope);
//                SoapObject response = (SoapObject) envelope.getResponse();
//                return SoapObjectToViolationTypes(response);
//            } catch (Exception ex) {
//                throw new RemoteException(ex.toString());
//            }
//        }
//
//        public ArrayList<Notification> getDriverLicenceNotifications(String licenceID) throws RemoteException {
//            SoapObject object = new SoapObject(Namespace, OperationGetDriverLicenceNotifications);
//            object.addProperty(PropertyLicenceID, licenceID);
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.dotNet = true;
//            envelope.setOutputSoapObject(object);
//            try {
//                mService.call(OperationAction + OperationGetDriverLicenceNotifications, envelope);
//                SoapObject response = (SoapObject)envelope.getResponse();
//                return SoapObjectToDriversNotifications(response);
//            } catch (Exception ex) {
//                throw new RemoteException(ex.toString());
//            }
//        }
//
//        public DriverLicence updateDLStatus(String licenceID, int statusTypeID, int officerID) throws RemoteException {
//
//            SoapObject object = new SoapObject(Namespace, OperationUpdateDLSatus);
//            object.addProperty(PropertyLicenceID, licenceID);
//            object.addProperty(PropertyStatusTypeID, String.valueOf(statusTypeID));
//            object.addProperty(PropertyOfficerID, String.valueOf(officerID));
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.dotNet = true;
//            envelope.setOutputSoapObject(object);
//            try {
//                mService.call(OperationAction + OperationUpdateDLSatus, envelope);
//                SoapObject response = (SoapObject) envelope.getResponse();
//                return SoapObjectToDriversLicense(response);
//            } catch (Exception ex) {
//                throw new RemoteException(ex.toString());
//            }
//        }

//        public class Import implements KvmSerializable {
//            String test;
//            public Import() {
//            }
//            public Import(String test) {
//                this.test = test;
//            }
//            public Object getProperty(int arg0) {
//                switch (arg0) {
//                    case 0:
//                        return test;
//                    default:
//                        return test;
//                }
//            }
//            public int getPropertyCount() {
//                return 1;
//            }
//            public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
//                switch (arg0) {
//                    case 0:
//                        arg2.type = PropertyInfo.STRING_CLASS;
//                        arg2.name = "PropertyLicenceID";
//                        break;
//                    default:
//                        break;
//                }
//            }
//            public void setProperty(int arg0, Object arg1) {
//                switch (arg0) {
//                    case 0:
//                        test = (String) arg1;
//                        break;
//                }
//            }
//        }
    }
