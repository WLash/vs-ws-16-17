JMS MessageDrivenBean-Beispiel
In dem Beispiel wird eine MessageDrivenBean aufgesetzt, welche als JMS-Listener fungiert. Im folgenden erkläre ich kurz die Schritte,
um das Konstrukt zum Laufen zu bekommen:

1. Wildfly herunterladen und entpacken
2. Unter folgendem Link den Punkt "Configuration" durchführen: http://eai-course.blogspot.de/2015/03/java-message-service-20-with-wildfly-8.html
3. Startet über Eure IDE oder per CMD Euren Wildfly mit dem Parameter -c standalone-full.xml (wie auch im Link von Link aus Punkt 2.
4. Nun Startet Ihr über Eure IDE oder per CMD den Befehl "mvn wildfly:deploy". Wenn der Befehl über CMD ausgeführt wird, müsst Ihr zuerst
Maven in Eure Systemumgebung integriert haben und den Befehl im Projektordner ausführen.
5. Das Projekt sollte nun deployed sein und die Seite http://localhost:8080/vs-ws-16-17/HelloWorld begrüßt Euch mit Hello World!
6. Die Klasse "Sender" führt eine einmalige Message in Richtung der erstellten Queue aus. Wenn Ihr die Klasse ausführt und den Wildfly-Server
beobachtet, seht Ihr, dass dieser die Nachricht empfängt und darauf reagiert.