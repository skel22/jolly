<?php

namespace App\Http\Controllers;

use App\Models\JolProduct;
use Illuminate\Http\Request;
use Illuminate\View\View; 
use Illuminate\Http\RedirectResponse; 

class JolProductController extends Controller
{

    public function welcome(Request $request)
    {
        return view('welcome');
    }

    public function index(Request $request)
{
    $jolproducts = JolProduct::latest()->paginate(5);

    return view('index', compact('jolproducts'))
        ->with('i', ($request->input('page', 1) - 1) * 5);
}


    public function create()
    {
        return view('create');
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
        $input['image'] = $profileImage; // Remove single quotes here
    }

    JolProduct::create($input);

    return redirect()->route('jolproducts.index')
                     ->with('success', 'Product added to shop with success.');
    }


    
    public function show(JolProduct $jolproduct)
    {
        return view('show', compact('jolproduct'));
    }


    public function edit(JolProduct $jolproduct)
    {
        return view('edit', compact('jolproduct'));
    }



    
    public function update(Request $request, JolProduct $jolproduct): RedirectResponse
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
        $input['image'] = $profileImage;
    } else {
        unset($input['image']);
    }

    $jolproduct->update($input);

    if ($jolproduct->wasChanged()) {
        // Changes were made
        return redirect()->route('jolproducts.index')
                ->with('success', 'Product updated with success.');
            } else {
            // No changes were made
            return redirect()->route('jolproducts.index')
                ->with('info', 'No changes were made to the product.');
        }
    }

    public function destroy(JolProduct $jolproduct)
    {
        $jolproduct->delete();

        return redirect()->route('jolproducts.index')
                    ->with('success', 'Product removed from shop with success.');
    }

}
