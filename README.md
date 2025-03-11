This repository was initially just meant to simulate mechanics from Pathfinder 2e.
The goal for doing so was the rebalance some of the existing classes, such as
the Swashbuckler, who suffered from the classic "feast or famine" trope. Their 
gameplay loop, gaining Panache and doing a Finisher, was incredibly easy to 
shatter. High Perception and Reflex spelled a Swash's doom. Changes were
implemented at my table in order to give them a leg-up. Funnily enough,
Paizo themselves implemented some of the changes implemented through the new
Bravado trait.

Will I come back to this project? 
Probably not. If I were to do any coding that was TTRPG adjacent, it would
probably be the creation of modules for Foundry VTT, and only if one was needed
at the table I'm running.

How does this work?
Inheritance is in full swing here. When instantiated, all character classes
(Ex. Fighter) extend the Character class. The given class will set class
appropriate features, such as the Fighter's bonus to hit, Rogue's Sneak Attack or
Ranger's Precision.

As for the weapons, each weapon extends the weapon group (Ex. Sukgung extends
Crossbow) and the weapon group extends the Weapon class.

Conditions were touched upon briefly, but if I recall correctly, only Frightened
and Off Guard were truly implemented.

As for the Enemy class, all it would do is set the AC as specified in Pathfinder's
AC progression for enemies and accept conditions.

What now?
I had the idea to simulate Pathfinder and make a text adventure game, however I haven't
found the time to do so. Perhaps in the future I'll reconsider and come back to it.
