@extends('jolproducts.layout')

@section('content')
<div class="row">
    <div class="col-lg-12 margin-tb">
        <div class="pull-left">
            <h2>Adicionar um Novo Produto</h2>
        </div>
        <div class="pull-right">
            <a class="btn btn-primary" href="{{ route('jolproducts.index') }}">Voltar</a>
        </div>
    </div>
</div>

@if($errors->any())
<div class="alert alert-danger">
    <strong>Ocorreu um erro</strong><br><br>
    <ul>
        @foreach ($errors->any() as $error)
        <li>{{ $error }}</li>
        @endforeach
    </ul>
</div>
@endif

<form action="{{ route('jolproducts.store') }}" method="POST" enctype="multipart/form-data">
    @csrf

    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="form-group">
                <strong>Nome: </strong>
                <input type="text" name="nome" class="form-control" placeholder="Nome">
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="form-control">
                <strong>Preço:</strong>"
                <input type="number" name="preco" class="form-control" placeholder="price">
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 text-center">
            
        </div>
    </div>
</form>