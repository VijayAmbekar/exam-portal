import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    userName: '',
    password: ''
  };

  constructor(private snackbar: MatSnackBar, private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log("Login button clicked");

    if(this.loginData.userName == null || this.loginData.userName.trim()=='' ) {
      this.snackbar.open('UserName is required', '', { duration: 3000});

      return;
    }

    if(this.loginData.password == null || this.loginData.password.trim()=='' ) {
      this.snackbar.open('Password is required', '', { duration: 3000});

      return;
    }

    // request server to generate token
    this.loginService.generateToken(this.loginData)
    .subscribe(
      (data: any) => {
        console.log("Success");
        console.log(data);

        // login
        this.loginService.loginUser(data.token);
        
        this.loginService.getCurrentUser().subscribe(
          (user: any) => {
            console.log("Success");
            console.log(user);
            this.loginService.setUserDetails(user);
            this.loginService.loginStatusSubject.next(true);

            // redirect to Homepage based on Role
            // ADMIN : admin-dashboard
            // BAU : Normal-dashboard

            if("ADMIN" == this.loginService.getUserRole()) {
              this.router.navigate(['/', 'admin-dashboard']);


            } else if("BAU" == this.loginService.getUserRole()) {
              this.router.navigate(['/', 'user-dashboard']);
            } else {
              // some other role
              this.loginService.logout();
            }

          },
          (error) => {
            console.log("Failure");
            console.log(error);            
          }
        );
        
      },

      (error) => {
        console.log("Failure");
        console.log(error);
        this.snackbar.open('Invalid details. Please try again!!', '', {duration:3000});
      }
    );
  }

}
