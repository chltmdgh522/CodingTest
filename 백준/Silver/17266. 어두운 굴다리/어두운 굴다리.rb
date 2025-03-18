N= gets.chomp.to_i
M=gets.chomp.to_i

place=gets.chomp.split.map(&:to_i)


start=place[0]
endd=N-place[place.size-1]
max=0
position=0
compare=0
compare = [start,endd].max

for i in 1...place.size
  position=place[i]-place[i-1]
  max=[max,position].max
end

if max % 2 ==1
  max+=1
end

max=[max/2,compare].max


puts max


