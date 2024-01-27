<?php

namespace App\Http\Controllers;

use App\Models\JolProduct;
use Illuminate\Http\Request;

class JolProductController extends Controller
{

    public function welcome(Request $request)
    {
        return view('welcome');
    }

    /**
     * Display a listing of the resource.
     */
    public function index(Request $request)
    {
        $jolProduct = JolProduct::latest()->paginate(5);

        return view('jolproduct.index', compact('jolProduct'))
            ->with('i', ($request->input('page', 1) - 1) * 5);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('jolproduct.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request): RedirectResponse
    {
        $request->validate([
            'name' => 'required',
            'price' => 'required',
            'image' => 'required|image|mimes:jpeg,png,jpg,gif,svg|max:2048',
        ]);

        $input = $request->all();

        if($image = $request->file('image')) {
            $destinationPath = 'images/';
            $profileImage = date('YmdHis') . "." . $image->getClientOriginalExtension();
            $image->move($destinationPath, $profileImage);
            $input['image'] = '$profileImage';
        }

        JolProduct::create($input);
    
        return redirect()->route('jolproducts.index')
                        ->with('success', 'Product added to shop with success.');
    }

    /**
     * Display the specified resource.
     */
    public function show(JolProduct $jolProduct): View
    {
        return view('jolproducts.show', compact('jolProduct'));
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(JolProduct $jolProduct)
    {
        return view('jolproducts.edit', compact('jolProduct'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, JolProduct $jolProduct): RedirectResponse
    {
        $request->validate([
            'name' => 'required',
            'price' => 'required',
        ]);

        $input = $request->all();

        if ($image = $request->file('image')) {
            $destinationPath = 'images/';
            $profileImage = date('YmdHis') . "." . $image->getClientOriginalExtension();
            $image->move($destinationPath, $profileImage);
            $input['image'] = "$profileImage";
        } else {
            unset ($input['image']);
        }

        $jolProduct->update($input);

        return redirect()->route('jolproducts.index')
                        ->with('success', 'Product updated with success.');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(JolProduct $jolProduct): RedirectResponse
    {
        $product->delete();

        return redirect()->route('jolproducts.index')
                        ->with('success', 'Product removed from shop with success.');
    }
}
