<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class JolProduct extends Model
{
    use HasFactory;

    protected $table = 'jolproducts'; 

    protected $fillable = [
        'name', 'price', 'image'
    ];
}

