<section class="py-8 py-8 max-w-full mt-12">

    <h1 class="text-lg font-bold mb-8 pb-2 border-b">
        {{$heading}}
    </h1>

    <div class="flex items-center">
        <aside class="w-full mb-6">
            <div>
                <ul class="flex">
                    <h4 class="font-semibold">Product links:</h4>
                    <li class="px-3">
                        <a href="/admin/products" class="{{request()->is('admin/products') ? 'text-red-500' : ''}}">All products</a>
                    </li>
            </div>
        </aside>
    </div>
    <main>
        <x-pannel>
            {{$slot}}
        </x-pannel>
    </main>

</section>

