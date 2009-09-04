Problem:
	AdMan is a software for "Advice Management". It is a highly customized e-mail notifier that will produce alert when email comes from your supervisor. It also displays the email and reads out the email for you.

Approach:
	We have a "Controller" class that is implemented as a thread. It periodically calls "MailFetcher" class to fetch newly received email. MailFetcher class uses java mail api to fetch emails from mail server. If newly received email is from your supervisor, then it will instantiate a "Notifier" object that will produce a sound for alert, display the e-mail and reads out the email for you. Notifier class uses FreeTTS software to convert text to speech. 

How to use the program:
	1. To add new alarm, go to AdMan menu. Then select "Add new Alarm". You will be prompted to give your advisor's name, email address and the noification sound.
	2. To delete alarm, go to AdMan menu. Then select "Delete Alarm".
	3. To view/ modify the currently set alarms, go to AdMan menu. Then select "Alarm Status". You can enable, disable previously set alarms.
	4. To exit, go to AdMan menu. Then select Exit.
