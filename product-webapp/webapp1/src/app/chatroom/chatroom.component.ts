import { Component, OnInit } from '@angular/core';
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
  getAllChats:ChatMessage[]=[];

  sendChats: ChatMessage = new ChatMessage;


  getChatRoom:ChatRoom[]=[];
  senderID:string = "S123";
  
  receiverNames!:string[];
  receiverName:string = "EFGH";
  messages!:string[];
  text:any;
  sender!:string[];

  constructor(private chatService:ChatroomService) { }

  ngOnInit(): void {
    this.chatService.getMessages().subscribe((data)=>{
      this.getChats = data;
      console.log(data);
      // this.getAllChats = data.filter((e)=>e.senderId == this.senderID);
      // this.receiverNames = data.map((e)=>e.recipientName);

      console.log(data.map(item => item.recipientName).filter((value, index, self) => self.indexOf(value) === index));
      this.receiverNames = data.map(item => item.recipientName).filter((value, index, self) => self.indexOf(value) === index);
      this.receiverNames.forEach((element,index)=>{
        if(element==this.senderName) this.receiverNames.splice(index,1);
     });
     
    })

    this.chatService.getChatroom().subscribe((data)=>{
      this.getChatRoom = data;
      console.log(data);
      console.log(data.filter((e)=>e.senderId == this.senderID));
      console.log(data.map((e)=>e.chatId));
      
      data.filter((e)=>e.senderId == this.senderID);
      
    })

    
  }
  senderId = "S123"
  recipientId = "R124"
  senderName = "ABCD";
  recipientName = "IJKL";

  // senderId = "R124"
  // recipientId = "S123"
  // senderName = "IJKL";
  // recipientName = "ABCD";

  sendMessage(){

    console.log(this.text);
    this.sendChats.senderId = this.senderId;    
    this.sendChats.senderName = this.senderName;
    this.sendChats.recipientId = this.recipientId;
    this.sendChats.recipientName = this.recipientName;
    this.sendChats.timestamp = Date.now().toString();
    this.sendChats.message = this.text;
    console.log(this.sendChats);
    
    this.chatService.postMessages(this.sendChats).subscribe((data)=>{
      console.log(data);
      console.log(this.sendChats);
      this.text = "";
      this.ngOnInit();
    })
    
  }

}
