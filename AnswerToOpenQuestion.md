# Open Question

Imagine an Android app that is used to send images, taken by a phone and sent to a backend server. The mobile phone is connected via a slow and unreliable GPRS connection and at least 3 images (2 containing a photographed document and one an image of a person) have to be transferred to the server.

What would be your approach to solve this task?
You are not expected to deliver a working program but rather to explain how you would approach solving this problem.

My approach:

I would create a session for each file upload on the client, 
which stores a pointer to the current byte of the file to upload.
As the connection is available I would stream starting from that byte to the server.
Every image uploaded would be tagged as such, so the next image can be uploaded after that.


