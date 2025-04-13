package com.example.capstone02.Service;

import com.example.capstone02.Model.Notification;
import com.example.capstone02.Model.User;
import com.example.capstone02.Model.Event;
import com.example.capstone02.Repository.NotificationRepository;
import com.example.capstone02.Repository.UserRepository;
import com.example.capstone02.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Boolean addNotification(Notification notification) {
        User user = userRepository.findUserById(notification.getUser_id());
        Event event = eventRepository.findEventById(notification.getEvent_id());

        if (user == null || event == null) {
            return false;
        }

        notification.setSent_at(LocalDate.now());
        notificationRepository.save(notification);
        return true;
    }

    public Boolean updateNotification(Integer id, Notification updatedNotification) {
        Notification oldNotification = notificationRepository.findNotificationById(id);
        if (oldNotification == null) {
            return false;
        }

        User user = userRepository.findUserById(updatedNotification.getUser_id());
        Event event = eventRepository.findEventById(updatedNotification.getEvent_id());

        if (user == null || event == null) {
            return false;
        }

        oldNotification.setUser_id(updatedNotification.getUser_id());
        oldNotification.setEvent_id(updatedNotification.getEvent_id());
        oldNotification.setMessage(updatedNotification.getMessage());
        oldNotification.setIs_read(updatedNotification.getIs_read());

        notificationRepository.save(oldNotification);
        return true;
    }

    public Boolean deleteNotification(Integer id) {
        Notification notification = notificationRepository.findNotificationById(id);
        if (notification == null) {
            return false;
        }

        notificationRepository.delete(notification);
        return true;
    }
}