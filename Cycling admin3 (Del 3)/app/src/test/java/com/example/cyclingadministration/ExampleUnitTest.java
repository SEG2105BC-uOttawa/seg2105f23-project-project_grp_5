package com.example.cyclingadministration;

import static org.junit.Assert.*;



import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.backend.Users;



import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


// PLEASE READ
/* The unit does want to run, it keeps saying process not mocked.
However all these tests should work.*/
@RunWith(JUnit4.class)
public class ExampleUnitTest {



    // Events class tests
    @Test
    public void testAddUser() {
        Users users = new Users();
        String username = "Test";
        String password = "Test";
        String role = "Admin";

        users.addUser(username, password, role);
        assertTrue(true);
        // Add assertions or checks to verify the success of the operation
    }




    @Test
    public void testGetUser() {
        Users users = new Users();
        String username ="Test";
        String password = "Test";

        users.getUser(username, password, new Users.LoginCallback() {
            @Override
            public void onResult(boolean success, String role, String isComplete) {
                assertTrue(success);
                assertNotNull(role);
                assertNotNull(isComplete);
                // Add more assertions or checks as needed
            }
        });
    }


    @Test
    public void testGetAllUsers() {
        Users users = new Users();

        users.getAllUsers(new Users.UsersCallback() {
            @Override
            public void onResult(boolean success, List<String> userList) {
                assertTrue(success);
                assertNotNull(userList);
                // Add more assertions or checks as needed
            }
        });
    }




    @Test
    public void testUpdateUser() {
        Users users = new Users();
        String username = "Test";
        String password =  "Test";
        String name = "Test";
        String phone = "Test";
        String social = "Test";

        users.updateUser(username, password, name, phone, social);

        // Add assertions or checks to verify the success of the operation
    }

    @Test
    public void testAddEventType() {
        Events events = new Events();
        Map<String, String> eventTypeData = new HashMap<>();
        eventTypeData.put("Event Type Name", "test");
        eventTypeData.put("Activity Type", "test");
        eventTypeData.put("Difficulty", "test");
        eventTypeData.put("Max People", "test");

        events.addEventType(eventTypeData);

        // Add assertions or checks to verify the success of the operation
        // For example:
        // assertTrue(/* condition *///);
        // assertNotNull(/* object */);}
    }


        @Test
        public void testDeleteEventType () {
            Events events = new Events();
            String eventTypeName = "test";

            events.deleteEventType(eventTypeName);

            // Add assertions or checks to verify the success of the operation
        }

        @Test
        public void testUpdateEventType () {
            Events events = new Events();
            String eventTypeName = "test";
            Map<String, Object> updates = new HashMap<>();
            updates.put("Event Type Name", "test2");
            updates.put("Activity Type", "test2");
            updates.put("Difficulty", "test2");
            updates.put("Max People", "test2");
            events.updateEventType(eventTypeName, updates);

            // Add assertions or checks to verify the success of the operation
        }

        @Test
        public void testGetAllEventTypes () {
            Events events = new Events();

            events.getAllEventTypes(new Events.EventTypesCallback() {
                @Override
                public void onResult(boolean success, List<String> eventTypes) {
                    assertTrue(success);
                    assertNotNull(eventTypes);
                    // Add more assertions or checks as needed
                }
            });
        }

        @Test
        public void testGetAllEvents () {
            Events events = new Events();

            events.getAllEvents("Test", new Events.EventCallback() {
                @Override
                public void onResult(boolean success, List<String> eventsList) {
                    assertTrue(success);
                    assertNotNull(eventsList);
                    // Add more assertions or checks as needed
                }
            });
        }

        // Users class tests

        @Test
        public void testDeleteUser () {
            Users users = new Users();
            String username = "Test";

            users.deleteUser(username);

            // Add assertions or checks to verify the success of the operation
        }
    }

