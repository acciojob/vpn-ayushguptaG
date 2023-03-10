package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{

        User user1= userRepository2.findById(userId).get();
        String coName= countryName.toUpperCase();

        if(user1.getMaskedIp()!= null){
            throw new Exception("Already connected");
        }
        else if(coName.equals(user1.getCountry().getCountryName().toString())){
            return user1;
        }
        else{
            throw new Exception("Unable to connect");
        }
    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository2.findById(userId).get();
        if(user.getConnected()==false)
            throw new Exception("Already disconnected");

        user.setMaskedIp(null);
        user.setConnected(false);
        userRepository2.save(user);
        return user;
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        try {
            User sender = userRepository2.findById(senderId).get();
            User receiver = userRepository2.findById(receiverId).get();

            String senderCountryCode  = "", receiverCountryCode = "";

            senderCountryCode = sender.getCountry().getCode();

            if (receiver.getConnected()==true) {
                String maskedId = receiver.getMaskedIp();
                receiverCountryCode = maskedId.substring(0,3);
            } else {
                receiverCountryCode = receiver.getCountry().getCode();
            }

            if ((senderCountryCode.equals(receiverCountryCode))) {
                return sender;
            }
            else{
                String countryName = sender.getCountry().getCountryName().name();
                connect(senderId,countryName);
                return sender;
            }
        }catch (Exception e){
            throw new Exception("Cannot establish communication");
        }
    }
}
