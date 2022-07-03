import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user = {
    userName: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  };

  constructor(private _snackBar: MatSnackBar, private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.user);
    
    if(this.user.userName=='' || this.user.userName == null) {
        this._snackBar.open("User is required", undefined, {duration:5000});

        return;
    }

    this.userService.addUser(this.user).subscribe(
      (data) => {
        console.log(data);
        this._snackBar.open("Success", undefined, {duration:5000});
      },
      (error) => {
        console.log(error);
        this._snackBar.open("Something went wrong", undefined, {duration:5000});
      }
    )
    
  }

}
