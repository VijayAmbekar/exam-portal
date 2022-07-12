import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-categories',
  templateUrl: './add-categories.component.html',
  styleUrls: ['./add-categories.component.css']
})
export class AddCategoriesComponent implements OnInit {

  category: any = {
    title: '',
    description: ''
  };

  constructor(private _categoryService: CategoryService, private _snackBar: MatSnackBar, private _router: Router) { }

  ngOnInit(): void {
  }

  formSubmit() {
    if(this.category.title== null || this.category.title.trim() =='') {
      
      this._snackBar.open('Title required!!','', {duration: 3000});
      return;
    }

    if(this.category.description== null || this.category.description.trim() =='') {
      this._snackBar.open('Description is required!!','', {duration: 3000});
      return;
    }

    this._categoryService.addCategory(this.category).subscribe(
      (data: any) => {
        console.log(data);
        Swal.fire('Done!!', 'Category is added with id: ' + data.cid, 'success');
        this._router.navigate(['/admin-dashboard/categories'])
      },
      (error) => {
        console.log(error);
        this._snackBar.open("Server error", undefined, {duration:5000});
      }
    );
  }

}
