import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes : any[]= [];

  constructor(private quizService: QuizService) { }

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

  //TODO: implement delete and update quiz

}
