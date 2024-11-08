"use client"

import { Template, ImageCard } from '@/components'
import {useImageService} from '@/resources/image/image-service'
import {Image} from '@/resources/image/image-resource'
import { useState } from 'react'


export default function GaleriaPage(){
 
    const userService = useImageService();
    const [images, setImages] = useState<Image[]>([])

    async function searchImage(){
           const result = await userService.buscar();
           setImages(result);
           console.table(images);
    }
        function renderImageCard(image: Image){
            return (
                <ImageCard nome={image.name} src={image.url} tamanho={image.size} dataUpload={image.uploadDate}/>
            )
        }

        function renderImageCards() {
           return images.map(renderImageCard)
        }
    return(
        
        <Template>
            <section className='flex flex-col items-center justify-center my-5'>
                <div className='flex space-x-4'>
                    <input type='text' className='border px-3 py-2 rounded-lg text-gray-900'/>
                        <select className='border px-4 py-2 rounded-lg text-gray-900'>
                            <option >All formats</option>
                        </select>
                        <button className='bg-blue-500 text-whit px-4 py-2 rounded-lg' onClick={searchImage} >Search</button>
                        <button className='bg-yellow-500 text-whit px-4 py-2 rounded-lg'>Add New
                        </button>
                </div>
            </section>
            <section className="grid grid-cols-3 gap-8">

            {
               renderImageCards()
            }
            
            </section>
            
        </Template>
    )
}