# Search Bookings based on different Criteria and Get Results in pages along with Page Metadata

You need to add functionality for searching Bookings based on different criteria

## Requirements

 - You need to add following APIs in BookingController
     
      - An Api with path `bookings/guestEmail` to search bookings done by a guest accepting body in form of BookingSearchDto and returning `Page<Booking>`
      - An Api with path `bookings/guestName` to search all bookings on Date when Mayor is staying, accepting body in form of BookingSearchDto and returning `Page<Booking>`. We just know mayor name and not date on which he is gonna stay. 
      - An Api with path `bookings/roomNumber_date` to search all bookings on a particular date which is occupying a particular room accepting body in form of BookingSearchDto and returning `Page<Booking>`.

 - You need to add implementation in following methods present in BookingService
    
    - Implement `getAllBookingDoneByGuest` with help of GuestRepo and Booking Repo
    - Implement `getAllBookingsOnDateWhenMayorIsStaying` with help of BookingRepo. In case mayor has done multiple bookings, pick first one.
    - Implement `getAllBookingsDoneForRoomOnParticularDate` with help of RoomRepo and BookingRepo.
    - All these methods will be returning results in form of Pages along with metadata about those pages.

## Hints
 - Nothing is needed from your side in pom.xml or application.properties
 - No new file need to be created.
 - Nothing need to be added/removed in dtos and models.
 - No new field need to be added, No field need to be removed or modified.
 - If you will try to run testcases without providing solutions, all Testcases will fail.