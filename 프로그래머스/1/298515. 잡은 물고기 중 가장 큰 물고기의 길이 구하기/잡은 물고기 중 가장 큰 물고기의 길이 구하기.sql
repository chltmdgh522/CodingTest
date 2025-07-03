-- 코드를 작성해주세요
select CONCAT(MAX(LENGTH), 'cm') AS MAX_LENGTH
FROM FISH_INFO
where LENGTH is not null 