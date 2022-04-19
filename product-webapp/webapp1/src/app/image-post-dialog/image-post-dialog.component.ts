import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { PostImage } from '../model/PostImage';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-image-post-dialog',
  templateUrl: './image-post-dialog.component.html',
  styleUrls: ['./image-post-dialog.component.css']
})
export class ImagePostDialogComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:PostService) { }

  ngOnInit(): void {
  }

  // description = new FormControl();

  file:any;
  upimage:any;

  postImage = this.fb.group({
    description:"",
  
   });

  onFileChanged(event: any) {
    console.log("onchange");
  
    this.file = event.target.files[0];
    console.log(this.file);
  
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]); 
    reader.onload = (_event) => { 
      console.log(reader.result);
        this.upimage = reader.result;
        console.log(this.upimage);
    }
  
  }

  post=new PostImage();
 
  sendPostImage(){
    console.log(this.file);
    const formData = new FormData();
    formData.append('file',this.file);
    this.post.description=this.postImage.value.description;
    this.post.like={likeCount:0}
    formData.append('post',JSON.stringify(this.post));

    this.service.sendImagePost(localStorage.getItem('loginId'),formData).subscribe(data =>{
      console.log(data);
      window.location.reload();
    })
    console.log();

  }
}
