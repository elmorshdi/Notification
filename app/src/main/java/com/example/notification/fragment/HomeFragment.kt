package com.example.notification.fragment

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import com.example.notification.*
import com.example.notification.MyApp.Companion.CHANNEL_ONE_ID
import com.example.notification.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private var mediaSession: MediaSessionCompat? = null

    companion object {

        var MESSAGES: ArrayList<Message> = ArrayList()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationManager = NotificationManagerCompat.from(requireContext())
        mediaSession = MediaSessionCompat(requireContext(), "tag")


        MESSAGES.add(Message("Good morning!", "Mostafa"))
        MESSAGES.add(Message("Hello", null))
        MESSAGES.add(Message("Hi!", "Mostafa2"))

        //pendingIntent FOR open MainActivity from notification
        val clickPendingIntent = PendingIntent.getActivity(
            requireContext(),
            0, Intent(requireContext(), MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
        )

        // handle Show  simple notification with  button to Show Toast button  click
        binding.showNotificationButton.setOnClickListener {
            //pendingIntent FOR open NotificationReceiver to Show Toast
            val actionIntent = PendingIntent.getBroadcast(
                requireContext(), 0,
                Intent(requireContext(), NotificationReceiver::class.java)
                    .putExtra("toastMessage", "message"),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
            )
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
            notificationManager.notify(1, notification)
        }

        /* handle Show  custom notification button click
           two layout one for collapsedView
           and other to expandedView*/
        binding.customNotification.setOnClickListener {

            // collapsedView initialize
            val collapsedView = RemoteViews(
                requireContext().packageName,
                R.layout.notification_collapsed
            )
            collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World!")

            // collapsedView initialize
            val expandedView = RemoteViews(
                requireContext().packageName,
                R.layout.notification_expanded
            )
            expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.download)
            expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)


            val notification = customNotification(
                context = requireContext(),
                title = "Custom Notification Title",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                expandedView = expandedView,
                collapsedView = collapsedView
            ).build()
            notificationManager.notify(22, notification)
        }

        // handle open fragment from notification button click
        binding.openFragmentNotification.setOnClickListener {
            //pendingIntent FOR open fragment from notification
            val pendingIntent = NavDeepLinkBuilder(requireContext())
                .setComponentName(MainActivity::class.java) // your destination activity
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.oneFragment)
                .createPendingIntent()


            val notification = simpleNotification(
                context = requireContext(),
                title = "simple Notification Title",
                body = "Body of simple Notification",
                smallIcon = R.drawable.ic_baseline_notifications,
                Channel_Id = CHANNEL_ONE_ID,
                contentIntent = pendingIntent
            ).build()
            notificationManager.notify(1, notification)
        }

        /* handle Show  chat notification button click
                          show simple chat group
                          with action button can open
                          input text and send message typed */
        binding.messagingWithReply.setOnClickListener {

            /* initialize PendingIntent for reply button
             in sdk N or above notification can update its content
            */
            val replyPendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                PendingIntent.getBroadcast(
                    context,
                    0, Intent(context, NotificationReceiver::class.java),
                    PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT// setting the mutability flag
                )
            } else {
                //start chat activity instead (PendingIntent.getActivity)
                clickPendingIntent
            }

            // remoteInput initialize
            val remoteInput: RemoteInput = RemoteInput.Builder("key_text_reply")
                .setLabel("Your answer...")
                .build()

            // replyAction initialize
            val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_notifications,
                "Reply",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()

            // initialize chat style
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
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

}