<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\JolProduct;
use Illuminate\Http\Request;
use App\Http\Resources\JolProductResource;
use Illuminate\Http\Response;

class JolProductAPIController extends Controller
{
    public function index()
    {
        $jolproducts = JolProduct::paginate(10);
        return response()->json($jolproducts);
    }



    public function show($id)
    {
        $jolproducts = JolProduct::find($id);

        if (!$jolproducts) {
            return response()->json(['message' => 'JolProduct not found'], 404);
        }

        return response()->json($jolproducts);
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required',
            'price' => 'required',
            'image' => 'required|image|mimes:jpeg,png,jpg,gif,svg|max:2048',
        ]);

        $input = $request->all();

        if ($image = $request->file('image')) {
            $destinationPath = 'imagens/';
            $profileImage = date('YmdHis') . "." . $image->getClientOriginalExtension();
            $image->move($destinationPath, $profileImage);
            $input['image'] = $profileImage;
        }

        $jolproducts = JolProduct::create($input);

        return response()->json($jolproducts, Response::HTTP_CREATED);
    }

    public function update(Request $request, $id)
    {
        $jolproducts = JolProduct::findOrFail($id);

        $request->validate([
            'name' => 'required',
            'price' => 'required',
        ]);

        $input = $request->all();

        if ($image = $request->file('image')) {
            $destinationPath = 'imagens/';
            $profileImage = date('YmdHis') . "." . $image->getClientOriginalExtension();
            $image->move($destinationPath, $profileImage);
            $input['image'] = $profileImage;
        } else {
            unset($input['image']);
        }

        $jolproducts->update($input);

        return response()->json(new JolProductResource($jolproducts));
    }

    public function destroy($id)
    {
        $jolproducts = JolProduct::findOrFail($id);
        $jolproducts->delete();

        return response()->json(['message' => 'Product removed from shop with success.']);
    }
}
