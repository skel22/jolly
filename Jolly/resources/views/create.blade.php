@extends('layout')
  
@section('content')

<div class="container mt-4">
    <div class="row">
        <div class="col-lg-12 margin-tb">
            <div class="pull-left">
                <h2>Add New Product</h2>
            </div>
            <div class="pull-right">
                <a class="btn btn-custom-primary" style="background: linear-gradient(45deg, #FF69B4, #FF1493); color: #FFFFFF; padding: 8px 12px; border-radius: 5px; text-decoration: none;" href="{{ route('jolproducts.index') }}"> Back</a>
            </div>
        </div>
    </div>
     
    @if ($errors->any())
        <div class="alert alert-danger">
            <strong>Whoops!</strong> There were some problems with your input.<br><br>
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif
     
    <form action="{{ route('jolproducts.store') }}" method="POST" enctype="multipart/form-data">
        @csrf
    
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6">
                <div class="form-group">
                    <strong>Name:</strong>
                    <input type="text" name="name" class="form-control" placeholder="Name">
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6">
                <div class="form-group">
                    <strong>Price:</strong>
                    <input type="text" class="form-control" name="price" placeholder="Product price">
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6">
                <div class="form-group">
                    <strong>Detail:</strong>
                    <textarea class="form-control" style="height:150px" name="detail" placeholder="Detail"></textarea>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-6">
                <div class="form-group">
                    <strong>Image:</strong>
                    <input type="file" name="image" class="form-control" placeholder="Image">
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 text-left mt-4">
                <button type="submit" class="btn btn-custom-primary" style="background: linear-gradient(45deg, #FF69B4, #FF1493); color: #FFFFFF; padding: 8px 12px; border-radius: 5px; text-decoration: none;">Submit</button>
            </div>
        </div>
    </form>
</div>
@endsection
