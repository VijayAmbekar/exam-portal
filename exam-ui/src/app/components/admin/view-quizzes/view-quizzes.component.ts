import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes : any[]= [];

  constructor(private quizService: QuizService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.quizService.quizzes().subscribe(
      (data: any) => {
        console.log(data);
        this.quizzes = data;
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'Unable to load quizzes from server', 'error');
      }
    );
  }

  onRemove(qid: number) {
    Swal.fire({
      icon: 'question',
      title: 'Are you sure?', 
      html: 'This will delete quiz : ' + this.quizzes.filter(q => q.qid == qid)[0].title,
      confirmButtonText: 'Delete',
      showCancelButton: true
      
    }).then((result) => {
      if(result.isConfirmed) {
          this.quizService.deleteQuiz(qid).subscribe(
            (data: any) => {
              console.log(data);
              Swal.fire('Success', 'quiz deleted', 'success');
      
              // remove from backing array
              this.quizzes = this.quizzes.filter((q) => q.qid  != qid);
            },
            (error) => {
              console.log(error);
              this._snackBar.open('Unable to load quizzes from server', '', {duration: 3000});
            }
          );
      }
    });


   
  }

  //TODO: implement update quiz

}
