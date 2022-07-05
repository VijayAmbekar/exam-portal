import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  userFullName ?: string;
  displayedColumns: string[] = ['fieldName', 'value'];
  dataSource: any[] = [];

  constructor(public loginService: LoginService) { }
  

  ngOnInit(): void {
      let user = this.loginService.getUser();

      this.userFullName = user.firstName + " " + user.lastName;

      // convert user data to table datasource

      this.dataSource.push({fieldName: 'User Name', value: user.username},);
      this.dataSource.push({fieldName: 'ID', value: user.id});
      this.dataSource.push({fieldName: 'Phone', value: user.phone});
      this.dataSource.push({fieldName: 'Email', value: user.email});
      this.dataSource.push({fieldName: 'Role', value: this.loginService.getUserRole()});
      this.dataSource.push({fieldName: 'Status', value: user.enabled ? 'ACTIVE' : 'DISABLED'});

  }

  ngOnDestroy(): void {
    // this.subscription?.unsubscribe();
  }

}
