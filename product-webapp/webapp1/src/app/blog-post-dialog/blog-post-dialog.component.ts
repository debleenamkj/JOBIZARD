import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { PostBlog } from '../model/PostBlog';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-blog-post-dialog',
  templateUrl: './blog-post-dialog.component.html',
  styleUrls: ['./blog-post-dialog.component.css']
})
export class BlogPostDialogComponent implements OnInit {

  constructor(private fb: FormBuilder, private service: PostService) { }

  ngOnInit(): void {
  }

  postBlog = this.fb.group({
    description: "",
    title: "",
    postBlog: ""
  });

  post = new PostBlog();

  sendPostBlog() {
    this.post.description = this.postBlog.value.description;
    this.post.title = this.postBlog.value.title;
    this.post.postBlog = this.postBlog.value.postBlog;
    const formData = new FormData();
    formData.append('post', JSON.stringify(this.post));
    this.service.sendBlogPost(localStorage.getItem('loginId'), formData).subscribe(data => {
      console.log(data);
      window.location.reload();
    });
  }
}
