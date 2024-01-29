<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\JolProductController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/admin/jolly', 'App\Http\Controllers\JolProductController@welcome');
Route::get('/admin/dashboard', 'App\Http\Controllers\JolProductController@index');
Route::resource('jolproducts', JolProductController::class);
Route::get('/admin/create', 'App\Http\Controllers\JolProductController@create');
Route::get('/admin/edit/{jolproduct}', 'App\Http\Controllers\JolProductController@edit');
