# Notification
# Functions to show different types of notifications

### simple notification to display a title and description with a button to display a toast

```python
            val notification = simpleNotification(
                context = requireContext(),
                title = "simple Notification Title",
                body = "Body of simple Notification",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                "Show Toast",
                actionIntent = actionIntent,
                contentIntent = clickPendingIntent
            ).build()
            notificationManager.notify(1, notification). ```
            
![Untitled](https://user-images.githubusercontent.com/53372814/176184882-5e50240f-8c58-4e02-9e25-becc5bfdc3d6.png)
### custom notification to show two layout one for collapsedView and other to expandedView
![image](https://user-images.githubusercontent.com/53372814/176185984-d0e9a85b-8a9d-47fb-b007-6bc6161fedac.png)![image](https://user-images.githubusercontent.com/53372814/176186681-ae24025c-c76a-4a53-9dd2-b3aec164d79d.png)
### group chat with the ability to reply to the chat
![image](https://user-images.githubusercontent.com/53372814/176185543-dbb703c5-f791-4b0d-afb4-4a2eeb7acfc2.png)
### Notification to open a fragment from the Notification
