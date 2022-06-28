# Notification
## Functions to show different types of notifications

### simple notification to display a title and description with a button to display a toast
![Untitled](https://user-images.githubusercontent.com/53372814/176184882-5e50240f-8c58-4e02-9e25-becc5bfdc3d6.png)

Just type in onCreate()     
```Kotlin
            val notification = simpleNotification( 
                context = requireContext(),
                title = "simple Notification Title",
                body = "Body of simple Notification",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                actionTitle = "Show Toast",
                actionIntent = actionIntent,
                contentIntent = clickPendingIntent
                ).build()
            notificationManager.notify(1, notification)
  ```  
### custom notification to show two layout one for collapsedView and other to expandedView
collapsedView 
```Kotlin
            val collapsedView = RemoteViews(
                requireContext().packageName,
                R.layout.notification_collapsed
                )
            collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World!")
```  
![image](https://user-images.githubusercontent.com/53372814/176185984-d0e9a85b-8a9d-47fb-b007-6bc6161fedac.png) 

expandedView 
```Kotlin 
            val expandedView = RemoteViews(
                requireContext().packageName,
                R.layout.notification_expanded 
                )
            expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.download)
            expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)
```  

![image](https://user-images.githubusercontent.com/53372814/176186681-ae24025c-c76a-4a53-9dd2-b3aec164d79d.png) 
 
```Kotlin 
            val notification = customNotification(
                context = requireContext(),
                title = "Custom Notification Title",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                expandedView = expandedView,
                collapsedView = collapsedView
            ).build()
            notificationManager.notify(22, notification)
 ``` 
### group chat with the ability to reply to the chat
![image](https://user-images.githubusercontent.com/53372814/176185543-dbb703c5-f791-4b0d-afb4-4a2eeb7acfc2.png) 

remoteInput 
```Kotlin 
            val remoteInput: RemoteInput = RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer...")
                .build()
``` 
replyAction 
```Kotlin 
            val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_notifications,
                "Reply",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()
```
 chat style
```Kotlin 
            val messagingStyle = NotificationCompat.MessagingStyle("Me")
            messagingStyle.conversationTitle = "Group Chat"
            for (chatMessage in MESSAGES) {
                val notificationMessage = NotificationCompat.MessagingStyle.Message(
                    chatMessage.text,
                    chatMessage.timestamp,
                    chatMessage.sender
                )
                messagingStyle.addMessage(notificationMessage)
            }
 ```
```Kotlin 

            val notification = simpleNotification(
                context = requireContext(),
                title = "simple Notification Title",
                body = "Body of simple Notification",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                contentIntent = clickPendingIntent,
                style = messagingStyle
            ).addAction(replyAction)
                .build()
            notificationManager.notify(5, notification)
 ```
### Notification to open a fragment from the Notification
pendingIntent FOR open fragment from notification
```Kotlin 
            val pendingIntent = NavDeepLinkBuilder(requireContext())
                .setComponentName(MainActivity::class.java) // your destination activity
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.oneFragment)
                .createPendingIntent()
``` 

