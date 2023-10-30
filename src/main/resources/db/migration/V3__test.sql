select * from db_socialapp.posts where id = '389cb2c1-bdf8-4534-9e93-46f741ae14db';
select * from db_socialapp.posts
         where unhex(id) = '389cb2c1-bdf8-4534-9e93-46f741ae14db'
           and status = 'PUBLIC'

select * from medias where post_id = 'd818ddc6-f3b4-4332-ae4c-d156642161c7'
