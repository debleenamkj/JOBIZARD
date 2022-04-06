import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { ChatMessage } from '../model/chat-message';
import { ChatRoom } from '../model/chat-room';
import { ChatroomService } from '../service/chatroom.service';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

  getChats:ChatMessage[]=[];
  getChatRoom:ChatRoom[]=[];
  senderID:string = "S123";
  receiverID!:string[];
  receiverNames!:string[];
  receiverName:string = "EFGH";
  senderName!:string;
  messages!:string[];
  text:any;
  sender!:string[];

  constructor(private chatService:ChatroomService) { }

  ngOnInit(): void {
    this.chatService.getMessages().subscribe((data)=>{
      this.getChats = data;
      console.log(data);
      console.log(data.filter((e)=>e.senderId == this.senderID));
      if(data.filter((e)=>e.senderId == this.senderID)){
      
      }
      this.receiverNames = data.map((e)=>e.recipientName);
      
      console.log(this.receiverName);
      for(let i = 0;i<this.receiverNames.length;i++){
      if(this.receiverName = this.receiverNames[i]){
        this.messages = data.map((e)=>e.message);
        this.sender = (data.map((e)=>e.senderName)),(data.map((e)=>e.message));

      }
    }
    })

    this.chatService.getChatroom().subscribe((data)=>{
      this.getChatRoom = data;
      console.log(data);
      console.log(data.filter((e)=>e.senderId == this.senderID));
      console.log(data.map((e)=>e.chatId));
      this.receiverID = data.map((e)=>e.recipientId);
      
      data.filter((e)=>e.senderId == this.senderID);
      
    })
  }

  sendMessage(){

    console.log(this.text);
    
  }
}
