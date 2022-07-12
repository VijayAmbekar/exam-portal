import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http: HttpClient) { }

  quizzes() {
    return this._http.get(`${baseUrl}/quiz/`);
  }

  addQuiz(quizData: any) {
    return this._http.post(`${baseUrl}/quiz/`, quizData);
  }
}
