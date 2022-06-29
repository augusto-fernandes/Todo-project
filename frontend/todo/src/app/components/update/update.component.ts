import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TodoService } from 'src/app/services/todo.service';
import { Todo } from 'src/app/models/todo';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  todo: Todo = {
    titulo: '',
    descricao: '',
    dataParaFinalizar: new Date(),
    finalizado: false
  }

  constructor(private router: Router, private service: TodoService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.todo.id = this.route.snapshot.paramMap.get('id')!;
    this.findByid();
  }


  findByid(): void {
    this.service.findById(this.todo.id).subscribe((resposta) =>{
      this.todo = resposta;
    })
  }

  update():void{
    this.formataData();
    this.service.update(this.todo).subscribe((resposta) => {
      this.service.message('Informações atualizada com sucesso!');
      this.router.navigate(['']);
    },error =>{
      this.service.message('falha ao atualizar To-do!');
      this.router.navigate(['']);
    })
  }
  

  cancel():void{
    this.router.navigate([''])
  }

  formataData():void{
    let data = new Date(this.todo.dataParaFinalizar)
    this.todo.dataParaFinalizar = `${data.getDate()}/${data.getMonth() +1}/${data.getFullYear()} `
  }


}
