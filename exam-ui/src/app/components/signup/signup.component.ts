import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

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
        this._snackBar.open("UserName is required", '', {
          duration:3 * 1000
        });

        return;
    }

    if(this.user.password=='' || this.user.password == null) {
      this._snackBar.open("Password is required", '', {
        duration:3 * 1000
      });

      return;
  }

    //TODO: validate other fields as well using regex and other field specific details


    this.userService.addUser(this.user).subscribe(
      (data: any) => {
        console.log(data);
        Swal.fire('Done!!', 'User is registered with id: ' + data.id, 'success');
        this._snackBar.open("Success", undefined, {duration:5000});
      },
      (error) => {
        console.log(error);
        this._snackBar.open(error.error, undefined, {duration:5000});
      }
    )
    
  }

}
