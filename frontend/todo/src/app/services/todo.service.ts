import { environment } from './../../environments/environment';
import { Todo } from './../models/todo';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  findAll(): Observable<Todo[]>{
    return this.http.get<Todo[]>(this.baseUrl);
  }
}
