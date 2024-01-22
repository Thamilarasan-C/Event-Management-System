package com.thamil.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}

}

// set attendee role as Attendee
// *printTicket entity will generate ticket token during registration will
// return ticketTokenNumber eventName AttendeeName Date
// entering ticket token number and get access for attendee to entering the
// event
// create event platform class
// entering event platform will update the ticket ispresent = true
// user also get the attended events by email id so even if the attendee
// registered as user they can get the previous attended

// eventPosters dto it will give all the events as a poster

// create dto for adding inputs

// TicketRegistration dto user id(try to get by token) ,List<Attendee> name
// email dob gender, List<email> eventId
// findAttendeeexistsbyId to check whether the attendee is already registered if
// not we have to enter full attendee details
// send attendee details to attendee table
// send attendeeCount ,userId ,eventId to registration table and get the
// registrationId
// find ticketDetailsId using eventId reduce ticketsAvailable by attendeeCount
// and send registrationId, ticketDetailsId ,all attendee id,userId to
// ticketTable

// EventInfo dto organizerId(try to get by token) , eventName ,date ,description
// ,venue , ticketcount , ticketprice(may be we can add list of ticketdetails)
// send event details to eventable and ticket details to ticket table

// create api which will allow all and they can see the happening events and can enter with token given to them
//create interfaces

// @Override
//   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       return repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is Not Found"));
//   }

//   public void validateUser(LoginRequest loginRequest) throws CustomException {
//     Authentication auth = authenticationManager.authenticate(
//         new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
//   }