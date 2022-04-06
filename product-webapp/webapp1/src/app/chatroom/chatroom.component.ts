import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// import * as Stomp from '@stomp/stompjs';
// import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {


  ngOnInit(): void {

  }
  constructor() { }

//   greetings: string[] = [];
//   disabled = true;
//   name!: string;
//   private stompClient:any;

  

//   setConnected(connected: boolean) {
//     this.disabled = !connected;

//     if (connected) {
//       this.greetings = [];
//     }
//   }

//   connect() {
//     const socket = new SockJS('http://localhost:8090/chat');
//     this.stompClient = Stomp.Stomp.over(socket);

//     const _this = this;
//     this.stompClient.connect({}, function (frame: string) {
//       _this.setConnected(true);
//       console.log('Connected: ' + frame);

//       _this.stompClient.subscribe('/topic', function (hello: { body: string; }) {
//         _this.showGreeting(JSON.parse(hello.body).greeting);
//       });
//     });
//   }

//   disconnect() {
//     if (this.stompClient != null) {
//       this.stompClient.disconnect();
//     }

//     this.setConnected(false);
//     console.log('Disconnected!');
//   }

//   sendName() {
//     this.stompClient.send(
//       '/chat',
//       {},
//       JSON.stringify({ 'name': this.name })
//     );
//   }

//   showGreeting(message: string) {
//     this.greetings.push(message);
//   }
}