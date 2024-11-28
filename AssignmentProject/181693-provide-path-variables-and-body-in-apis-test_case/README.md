# Learn how to provide path variables and Body in your APIs

## Requirements

#### You are given a `BookingController` in which you have to convert given methods into APIs. 

#### Also make modifications in method signatures, so that parameters passed in route/path can be consumed inside methods.

0. If you don't know about `@PathVariable` and `@RequestBody` , please read about them once.
1. You need to convert given methods into APIs with following path/route 
       
-  getBookingById -> `/booking/{bookingId:[\d]+}`
-  getBookingByGuestNameAndDate -> `/booking/guest/{guest}/date/{date}`
-  listBookingsOfParticularDate -> `/booking/date/{date}`
-  updateBooking -> `/booking/{bookingId:[\d]+}`

2. You need to determine, which API method will suit best for each of these APIs by looking at methods already given in `BookingController` class.

3. No change is required in `models` or `dtos`. Just refer them for your reference. 

## Hints
1. You don't need to create any new file.
2. Please don't change any dependency in any file.
3. If you will try to run testcases without adding solution, all Testcases will fail.
  