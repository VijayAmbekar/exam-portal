import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  categories: any[] = [];

  quizData = {
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    isActive: false,
    category:  {
        cid: ''
    }
  };

  constructor(private _categoryService: CategoryService, private _snackBar: MatSnackBar,
    private _quizService: QuizService, private _router: Router) { }

  ngOnInit(): void {
    this._categoryService.categories().subscribe(
      (data: any) => {
        this.categories = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error!', 'Error in loading data', 'error');
      }
    );
  }

  onSubmit() {

    if(null == this.quizData.title || '' == this.quizData.title.trim()) {
      this._snackBar.open('Quiz title is mandatory', '', {duration: 3000});

      return;
    }

    //TODO: add more field level validations
    this._quizService.addQuiz(this.quizData).subscribe(
      (data: any) => {
        console.log(data);
        Swal.fire('Success!', 'Added Quiz with id: ' + data.qid, 'success');
        this._router.navigate(['/admin-dashboard/quizzes']);
      },
      (error) => {
        console.log(error);
        this._snackBar.open('Something went wrong', '', {duration:3000});
      }
    );
    
  }

}
