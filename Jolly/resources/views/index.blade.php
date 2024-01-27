@extends('jolproduct.layout')

@section('content')

<div class="row justify-content-center mt-3">
    <div class="col-md-12">
    
    @if ($message = Session::get('success'))
        <div class="alert alert-success">
            <p>{{ $message }}</p>
        </div>
    @endif
     
    <table class="table table-bordered">
        <tr>
            <th>No</th>
            <th>Image</th>
            <th>Name</th>
            <th>Details</th>
            <th width="280px">Action</th>
        </tr>
        @foreach ($jolProduct as $jolProduct)
        <tr>
            <td>{{ ++$i }}</td>
            <td><img src="/images/{{ $jolProduct->image }}" width="100px"></td>
            <td>{{ $jolProduct->name }}</td>
            <td>{{ $jolProduct->detail }}</td>
            <td>
                <form action="{{ route('jolproduct.destroy', $jolProduct->id) }}" method="POST">
     
                    <a class="btn btn-info" href="{{ route('jolproduct.show', $jolProduct->id) }}">Show</a>
      
                    <a class="btn btn-primary" href="{{ route('jolproduct.edit', $jolProduct->id) }}">Edit</a>
     
                    @csrf
                    @method('DELETE')
        
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        @endforeach
    </table>
    
    {!! $jolProduct->links() !!}
</div>
@endsection