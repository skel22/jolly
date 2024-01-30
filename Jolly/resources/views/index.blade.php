@extends('layout')

@section('content')

<div class="row justify-content-center mt-3">
    <div class="col-md-12">
    
    @if ($message = Session::get('success'))
        <div class="alert alert-success">
            <p>{{ $message }}</p>
        </div>
    @endif

    <div class="mb-3">
        <a href="{{ route('jolproducts.create') }}" class="btn btn-primary bg-gradient-to-r from-pink-500 to-purple-500 text-white px-4 py-2 rounded-full">Create New Jol Product</a>
    </div>

    <table class="table table-bordered w-full">
        <thead>
            <tr>
                <th>No</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th width="280px">Action</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($jolproducts as $jolProduct)
                <tr>
                    <td>{{ ++$i }}</td>
                    <td><img src="/images/{{ $jolProduct->image }}" width="100px"></td>
                    <td>{{ $jolProduct->name }}</td>
                    <td>{{ $jolProduct->price }}</td>
                    <td>
                        <form action="{{ route('jolproducts.destroy', $jolProduct->id) }}" method="POST">
                            <a class="btn btn-info" href="{{ route('jolproducts.show', $jolProduct->id) }}">Show</a>
                            <a class="btn btn-primary" href="{{ route('jolproducts.edit', $jolProduct->id) }}">Edit</a>
                            
                            @csrf
                            @method('DELETE')    
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </tbody>
    </table>
    
    {!! $jolproducts->links() !!}

    <x-footer/>
</div>
@endsection
